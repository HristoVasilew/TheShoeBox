package TheShoeBox.TheShoeBox.service.impl;

import TheShoeBox.TheShoeBox.model.bindng.ShoeBindingModel;
import TheShoeBox.TheShoeBox.model.entity.ShoeCategoryEntity;
import TheShoeBox.TheShoeBox.model.entity.ShoeEntity;
import TheShoeBox.TheShoeBox.model.entity.UserEntity;
import TheShoeBox.TheShoeBox.model.service.ShoeServiceModel;
import TheShoeBox.TheShoeBox.repository.CategoryRepository;
import TheShoeBox.TheShoeBox.repository.ShoeRepository;
import TheShoeBox.TheShoeBox.repository.UserRepository;
import TheShoeBox.TheShoeBox.service.CategoryEntityService;
import TheShoeBox.TheShoeBox.service.ShoeService;
import TheShoeBox.TheShoeBox.service.UserEntityService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ShoeServiceImpl implements ShoeService {
    private final ShoeRepository shoeRepository;
    private final ModelMapper modelMapper;
    private final UserEntityService userEntityService;
    private final UserRepository userRepository;
    private final CategoryEntityService categoryEntityService;
    private final CategoryRepository categoryRepository;

    public ShoeServiceImpl(ShoeRepository shoeRepository, ModelMapper modelMapper, UserEntityService userEntityService, UserRepository userRepository, CategoryEntityService categoryEntityService, CategoryRepository categoryRepository) {
        this.shoeRepository = shoeRepository;
        this.modelMapper = modelMapper;
        this.userEntityService = userEntityService;
        this.userRepository = userRepository;
        this.categoryEntityService = categoryEntityService;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public ShoeServiceModel addShoe(ShoeBindingModel shoeBindingModel, String userIdentifier) {

        UserEntity userEntity = userRepository.findByEmail(userIdentifier).orElseThrow();

        ShoeServiceModel shoeService = modelMapper.map(shoeBindingModel, ShoeServiceModel.class);
        ShoeEntity shoe = modelMapper.map(shoeService, ShoeEntity.class);

        shoe.setCreatedOn(LocalDateTime.now());
        shoe.setCreator(userEntity);
        ShoeCategoryEntity cat = categoryRepository.findShoeCategoryEntityByName(shoeBindingModel.getShoeCategoryEnum());
        shoe.setShoeCategoryEntity(cat);

        ShoeEntity savedShoe = shoeRepository.save(shoe);

        return modelMapper.map(savedShoe, ShoeServiceModel.class);
    }
}
