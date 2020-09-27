package com.ktm.cache;

import com.ktm.dto.TagDTO;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class TagsCache {

    static List<TagDTO> tags;


    static class InnerTag {

        static {
            TagDTO lang = new TagDTO();
            lang.setCategoryName("开发语言");
            lang.setTagNames(Arrays.asList("Java", "python", "c", "golang", "c++", "c#", "javascript", "php", "perl", "ruby", "lua", "node.js", "erlang", "scala", "bash", "actionscript", "typescript"));

            TagDTO framework = new TagDTO();
            framework.setCategoryName("平台框架");
            framework.setTagNames(Arrays.asList("Spring", "Spring Boot", "Spring Mvc", "mybatis", "jpa", "Spring Cloud","hibernate"));


            TagDTO server = new TagDTO();
            server.setCategoryName("服务器");
            server.setTagNames(Arrays.asList("nginx", "tomcat", "linux", " apache"));


            TagDTO database = new TagDTO();
            database.setCategoryName("数据库");
            database.setTagNames(Arrays.asList("mysql", "oracle", "h2", "redis", "mongodb","elasticsearch","memcached"));

            TagDTO developKit = new TagDTO();
            developKit.setCategoryName("开发工具");
            developKit.setTagNames(Arrays.asList(
                    "eclipse"
                    , "vs code"
                    , "maven"
                    , "gradle"
                    , "webstorm"
                    , "ide "
                    , "eclipse"
                    , "xcode"
                    , "intellij-idea"
                    , "macosx"
                    , "bundle"
                    , "vim"
                    , "emacs"
                    , "intellij-idea"
                    , "textmate"
                    , "sublime-text"
                    , "visual-studio"
                    , "git"
                    , "github"
                    , "svn"
                    , "hg"
                    , "docker ci"));
            tags = Arrays.asList(lang, framework, server, database, developKit);
        }

        public static List<TagDTO> initTagDTO() {
            return tags;
        }

    }


    public static List<TagDTO> getTagDTO() {
        return InnerTag.initTagDTO();
    }


    public static String invalidTags(String tag) {
        String[] tags = tag.split(",");
        List<TagDTO> tagDTOs = getTagDTO();
        List<String> tagList = tagDTOs.stream().flatMap(tagDTO -> tagDTO.getTagNames().stream()).collect(Collectors.toList());

        return Arrays.stream(tags).filter(t -> !tagList.contains(t)).collect(Collectors.joining(","));

    }

}



