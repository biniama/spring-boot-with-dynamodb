package com.biniam.dto;

import com.biniam.entity.Placement;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Content {

    private String contentId;
    private Integer value;
    private String profileId;
    private String description; // Profile description
    private List<Placement> placements;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date expirationDate;
}
