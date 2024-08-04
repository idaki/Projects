package bg.softuni.tournamentservice.model.ExportDto;

import jakarta.persistence.Column;

import java.util.Date;

public class TournamentInfoExportDTO {
    private Long id;

    @Column
    private String name;

    @Column
    private String category;

    @Column
    private String summary;

    @Column
    private Date startDate;

    @Column
    private Date endDate;

    @Column
    private int numberOfTeams;

    @Column
    private int teamSize;
}
