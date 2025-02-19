package com.industry62.decathlon.config;

import static org.mapstruct.ReportingPolicy.IGNORE;

import org.mapstruct.MapperConfig;

@MapperConfig(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface MapStructConfig {

}
