package com.biniam.dto;

import com.biniam.entity.Info;
import com.biniam.entity.Profile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InfoProfile {

    Info Info;
    Profile Profile;
}
