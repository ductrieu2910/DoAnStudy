package com.example.doanstudy.service.impl;

import ch.qos.logback.core.util.StringUtil;
import com.example.doanstudy.model.dto.CategoriesDTO;
import com.example.doanstudy.model.entity.CategoriesEntity;
import com.example.doanstudy.model.entity.CourseEntity;
import com.example.doanstudy.repository.CategoriesRepository;
import com.example.doanstudy.service.CategoriesService;
import jakarta.persistence.criteria.Join;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoriesImpl implements CategoriesService {
    private  final CategoriesRepository categoriesRepository;
    private final ModelMapper modelMapper;

    @Override
    public Page<CategoriesEntity> findAll(String search, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber -1, 5);
        Specification<CategoriesEntity> specs = Specification.where(null);
        if(Objects.nonNull(search)){
            final String key = "%"+search+"%";
            specs = specs.or((r, q, b) -> b.like(r.get("name"),key));
        }
        Page<CategoriesEntity> page = categoriesRepository.findAll(specs,pageable);
        return page;
    }

    @Override
    public List<CategoriesEntity> findAll() {
        List<CategoriesEntity> categoriesEntityList = categoriesRepository.findAll();

        return categoriesEntityList;
    }

    @Override
    public List<CategoriesDTO> categoriesDTOList() {
        List<CategoriesEntity> categoriesEntityList = categoriesRepository.findAll();
        List<CategoriesDTO> categoriesDTOList = categoriesDTOList("0", categoriesEntityList);
        return categoriesDTOList;
    }


    private List<CategoriesDTO> categoriesDTOList(String id, List<CategoriesEntity> categoriesEntityList){
        
        List<CategoriesDTO> categories = categoriesEntityList.stream()
                .filter(x -> StringUtils.compare(id, x.getParentId())==0)
                .map(x -> modelMapper.map(x, CategoriesDTO.class))
                .collect(Collectors.toList());
        for (CategoriesDTO category: categories) {
            List<CategoriesDTO> childs = categoriesDTOList(category.getId(), categoriesEntityList);
            // 12-13
            String ids = childs.stream().map(CategoriesDTO::getId).collect(Collectors.joining("-"));
            category.setUrlId(category.getId()+"-"+ids);
            category.setChilds(childs);
        }
        return categories;
    }


    @Override
    public List<CategoriesEntity> findByParentIdIsNotNull() {
        return categoriesRepository.findByParentIdIsNotNull();
    }

    @Override
    public List<CategoriesEntity> findAllByParentIdIsNull() {
        return categoriesRepository.findAllByParentIdIsNull();
    }

    @Override
    public List<CategoriesEntity> findAllByParentId(String parentId) {
        return categoriesRepository.findAllByParentId(parentId);
    }


    @Override
    public CategoriesEntity SaveorUpdate(CategoriesEntity categoriesEntity) {
        try {
            return categoriesRepository.save(categoriesEntity);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public CategoriesEntity findById(String id) {
        try {
                return categoriesRepository.findById(id).orElse(null);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public CategoriesEntity remove(CategoriesEntity categoriesEntity) {
        try {
            categoriesRepository.delete(categoriesEntity);
            return categoriesEntity;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }


}
