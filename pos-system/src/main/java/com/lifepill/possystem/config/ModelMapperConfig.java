package com.lifepill.possystem.config;

import com.lifepill.possystem.dto.requestDTO.ItemSaveRequestCategoryDTO;
import com.lifepill.possystem.entity.ItemCategory;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
//    @Bean
//    public ModelMapper modelMapper(){
//        return new ModelMapper();
//    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        // Add explicit mapping for categoryId
        modelMapper.typeMap(ItemSaveRequestCategoryDTO.class, ItemCategory.class)
                .addMapping(ItemSaveRequestCategoryDTO::getCategoryId, ItemCategory::setCategoryId);

        return modelMapper;
    }
}
