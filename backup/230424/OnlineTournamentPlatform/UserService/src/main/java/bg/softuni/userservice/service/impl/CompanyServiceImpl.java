package bg.softuni.userservice.service.impl;


import bg.softuni.userservice.models.dto.gson.ConsumerImportDTO;
import bg.softuni.userservice.models.entity.business.company.Company;
import bg.softuni.userservice.repository.CompanyRepository;
import bg.softuni.userservice.service.CompanyService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final ModelMapper modelMapper;

    private final String FILE_PATH = "src/main/resources/files/users.json";
    private final Gson gson;
    private final ResourceLoader resourceLoader;


    public CompanyServiceImpl(CompanyRepository companyRepository, ModelMapper modelMapper, Gson gson, ResourceLoader resourceLoader) {
        this.companyRepository = companyRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.resourceLoader = resourceLoader;
    }



    @Override
    public boolean areImported() {
        return this.companyRepository.count() > 0;
    }

    @Override
    public String readUsersFromFile() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:files/users.json");
        try (InputStream inputStream = resource.getInputStream()) {
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        }
    }

    @Override
    public String importUsers() throws IOException {
        ConsumerImportDTO[] consumerImportDTOS = gson.fromJson(this.readUsersFromFile(), ConsumerImportDTO[].class);

        int newCompaniesCount = 0;
        int skippedCompaniesCount = 0;

        for (ConsumerImportDTO consumerImportDTO : consumerImportDTOS) {
            Optional<Company> existingCompanyOptional = this.companyRepository.findByEmail(consumerImportDTO.getEmail());

            if (existingCompanyOptional.isEmpty()) {
                Company company = modelMapper.map(consumerImportDTO, Company.class);

                this.companyRepository.saveAndFlush(company);
                newCompaniesCount++;
            } else {
                System.out.printf("Company with email %s already exists. Skipping...\n", consumerImportDTO.getEmail());
                skippedCompaniesCount++;
            }
        }

        return String.format("CompanyImport Completed. New Companies: %d, Skipped Companies: %d", newCompaniesCount, skippedCompaniesCount);
    }

}

