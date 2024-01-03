package com.artempact.backend.mysqlArtempact.dto.profile.profileCreative.projectCreative;

import com.artempact.backend.mysqlArtempact.entity.profile.profileCreative.ProfileCreative;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ProjectCreativeDTO {
    private String name;
    // Da fare
    //private Set<PhotoProjectCreative> photoProjectCreatives;
    private String year;
    private String type;
    private String description;
    private String customer;
    private String link;
    private ProfileCreative profileCreative;
}
