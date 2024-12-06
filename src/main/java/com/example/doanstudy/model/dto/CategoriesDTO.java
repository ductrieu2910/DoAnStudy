package com.example.doanstudy.model.dto;


import java.util.List;

public class CategoriesDTO {
    private String id;
    private String name;
    private String description;
    private String parentId;
    private String urlId;
    private List<CategoriesDTO> childs;

    public CategoriesDTO() {
    }

    public CategoriesDTO(String id, String name, String description, String parentId, List<CategoriesDTO> childs) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.parentId = parentId;
        this.childs = childs;
    }

    public String getUrlId() {
        return urlId;
    }

    public void setUrlId(String urlId) {
        this.urlId = urlId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public List<CategoriesDTO> getChilds() {
        return childs;
    }

    public void setChilds(List<CategoriesDTO> childs) {
        this.childs = childs;
    }
}
