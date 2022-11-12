package com.bbms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InviteMember {
	private String email;
    private String id;
    private String name;
    private String url;
    private String workspaceId;
}
