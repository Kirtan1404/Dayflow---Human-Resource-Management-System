package com.DTO;

import java.util.UUID;

import lombok.Data;

@Data
public class LeaveActionRequest {

    private UUID leaveId;
    private String status; // APPROVED / REJECTED
    private String adminComment;

    // getters and setters
}
