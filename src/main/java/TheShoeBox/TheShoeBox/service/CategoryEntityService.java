package TheShoeBox.TheShoeBox.service;

import TheShoeBox.TheShoeBox.model.entity.ShoeCategoryEntity;
import TheShoeBox.TheShoeBox.model.entity.enums.ShoeCategoryEnum;

public interface CategoryEntityService {
    void initCategories();
    ShoeCategoryEntity getCategoryByName(ShoeCategoryEnum name);
}
