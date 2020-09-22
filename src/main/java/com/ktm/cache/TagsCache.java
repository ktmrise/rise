package com.ktm.cache;

import com.ktm.dto.TagDTO;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class TagsCache {

    public static List<TagDTO> getTagDTO() {
        TagDTO lang = new TagDTO();
        lang.setCategoryName("开发语言");
        lang.setTagNames(Arrays.asList("Java", "python"));

        TagDTO framework = new TagDTO();
        framework.setCategoryName("平台框架");
        framework.setTagNames(Arrays.asList("Spring", "Spring Boot"));

        return Arrays.asList(lang, framework);

    }

    public static String invalidTags(String tag) {
        String[] tags = tag.split(",");
        List<TagDTO> tagDTOs = getTagDTO();
        List<String> tagList = tagDTOs.stream().flatMap(tagDTO -> tagDTO.getTagNames().stream()).collect(Collectors.toList());

        return Arrays.stream(tags).filter(t -> !tagList.contains(t)).collect(Collectors.joining(","));

    }

}
