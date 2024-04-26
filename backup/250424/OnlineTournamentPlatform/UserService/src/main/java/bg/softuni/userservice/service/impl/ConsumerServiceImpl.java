package bg.softuni.userservice.service.impl;


import bg.softuni.userservice.models.dto.gson.ConsumerImportDTO;
import bg.softuni.userservice.models.entity.consumer.Consumer;
import bg.softuni.userservice.repository.ConsumerRepository;
import bg.softuni.userservice.service.ConsumerService;
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
public class ConsumerServiceImpl implements ConsumerService {

    private final ConsumerRepository consumerRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ResourceLoader resourceLoader ;


    public ConsumerServiceImpl(ConsumerRepository consumerRepository, ModelMapper modelMapper, Gson gson, ResourceLoader resourceLoader) {
        this.consumerRepository = consumerRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public boolean areImported() {
        return this.consumerRepository.count() > 0;
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

        int newConsumersCount = 0;
        int skippedConsumersCount = 0;

        for (ConsumerImportDTO consumerImportDTO : consumerImportDTOS) {
            Optional<Consumer> existingConsumerOptional = this.consumerRepository.findByEmail(consumerImportDTO.getEmail());

            if (existingConsumerOptional.isEmpty()) {
                Consumer consumer = modelMapper.map(consumerImportDTO, Consumer.class);
                this.consumerRepository.saveAndFlush(consumer);
                newConsumersCount++;
            } else {
                System.out.printf("Consumer with email %s already exists. Skipping...\n", consumerImportDTO.getEmail());
                skippedConsumersCount++;
            }
        }

        return String.format("CompanyImport Completed. New Consumers: %d, Skipped Consumers: %d", newConsumersCount, skippedConsumersCount);
    }
}
