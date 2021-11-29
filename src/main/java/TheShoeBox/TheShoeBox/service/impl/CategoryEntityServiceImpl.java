package TheShoeBox.TheShoeBox.service.impl;

import TheShoeBox.TheShoeBox.model.entity.ShoeCategoryEntity;
import TheShoeBox.TheShoeBox.model.entity.enums.ShoeCategoryEnum;
import TheShoeBox.TheShoeBox.repository.CategoryRepository;
import TheShoeBox.TheShoeBox.service.CategoryEntityService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CategoryEntityServiceImpl implements CategoryEntityService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryEntityServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void initCategories() {
        if(categoryRepository.count() > 0){
            return;
        }

        Arrays.stream(ShoeCategoryEnum.values())
                .forEach(c -> {

                    ShoeCategoryEntity categoryEntity = new ShoeCategoryEntity();
                    categoryEntity.setName(c);

                    categoryRepository.save(categoryEntity);
                });
    }

    @Override
    public ShoeCategoryEntity getCategoryByName(ShoeCategoryEnum name) {
        return categoryRepository.getCategoryEntityByName(name);
    }

    @Override
    public ShoeCategoryEntity findShoeCategoryEntityByName(ShoeCategoryEnum name) {
        return categoryRepository.findShoeCategoryEntityByName(name);
    }
}
