package com.findtripmate.modules.membership.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberResponseDTO {

    private Long userId;
    private String name;
    private String role;
}