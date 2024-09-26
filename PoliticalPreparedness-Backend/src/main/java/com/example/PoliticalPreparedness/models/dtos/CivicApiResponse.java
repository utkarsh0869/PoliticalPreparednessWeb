package com.example.PoliticalPreparedness.models.dtos;

import java.util.List;

public class CivicApiResponse {

    private List<OfficialDto> officials;

    public List<OfficialDto> getOfficials() {
        return officials;
    }

    public void setOfficials(List<OfficialDto> officials) {
        this.officials = officials;
    }
}
