package TheShoeBox.TheShoeBox.service.impl;

import TheShoeBox.TheShoeBox.model.bindng.ShoeBindingModel;
import TheShoeBox.TheShoeBox.model.entity.*;
import TheShoeBox.TheShoeBox.model.entity.enums.UserRoleEnum;
import TheShoeBox.TheShoeBox.model.service.ShoeServiceModel;
import TheShoeBox.TheShoeBox.model.view.ShoeViewModel;
import TheShoeBox.TheShoeBox.repository.CategoryRepository;
import TheShoeBox.TheShoeBox.repository.ShoeConditionRepository;
import TheShoeBox.TheShoeBox.repository.ShoeRepository;
import TheShoeBox.TheShoeBox.repository.UserRepository;
import TheShoeBox.TheShoeBox.service.CategoryEntityService;
import TheShoeBox.TheShoeBox.service.ShoeConditionService;
import TheShoeBox.TheShoeBox.service.ShoeService;
import TheShoeBox.TheShoeBox.service.UserEntityService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShoeServiceImpl implements ShoeService {
    private final ShoeRepository shoeRepository;
    private final ModelMapper modelMapper;
    private final UserEntityService userEntityService;
    private final UserRepository userRepository;
    private final CategoryEntityService categoryEntityService;
    private final CategoryRepository categoryRepository;
    private final ShoeConditionService shoeConditionService;
    private final ShoeConditionRepository shoeConditionRepository;

    public ShoeServiceImpl(ShoeRepository shoeRepository, ModelMapper modelMapper, UserEntityService userEntityService, UserRepository userRepository, CategoryEntityService categoryEntityService, CategoryRepository categoryRepository, ShoeConditionService shoeConditionService, ShoeConditionRepository shoeConditionRepository) {
        this.shoeRepository = shoeRepository;
        this.modelMapper = modelMapper;
        this.userEntityService = userEntityService;
        this.userRepository = userRepository;
        this.categoryEntityService = categoryEntityService;
        this.categoryRepository = categoryRepository;
        this.shoeConditionService = shoeConditionService;
        this.shoeConditionRepository = shoeConditionRepository;
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
        shoe.setImageUrl(shoeBindingModel.getImageUrl());
        ShoeConditionEntity condition = shoeConditionRepository.findShoeConditionEntityByName(shoeBindingModel.getConditionEnum());

        shoe.setShoeConditionEntity(condition);
        ShoeEntity savedShoe = shoeRepository.save(shoe);

        return modelMapper.map(savedShoe, ShoeServiceModel.class);
    }

    @Override
    public List<ShoeViewModel> findAllShoes() {
        return shoeRepository.findAll()
                .stream().map(s-> modelMapper.map(s, ShoeViewModel.class))
                .collect(Collectors.toList());

    }

    @Override
    public ShoeViewModel findById(Long id, String name) {
        ShoeViewModel shoeViewModel = shoeRepository.findById(id)
                .map(s -> mapDetailsView(name, s))
                .get();
        return shoeViewModel;
    }

    @Override
    public void deleteOffer(Long id) {
        shoeRepository.deleteById(id);
    }

    private ShoeViewModel mapDetailsView(String currentUser, ShoeEntity offer) {
        ShoeViewModel shoeViewModel = this.modelMapper.map(offer, ShoeViewModel.class);
        shoeViewModel.setCanDelete(isOwner(currentUser, offer.getId()));
        shoeViewModel.setModel(offer.getModel());
        shoeViewModel.setBrand(offer.getBrand());
        shoeViewModel.setSellerFullName(
                offer.getCreator().getFirstName() + " " + offer.getCreator().getLastName());
        return shoeViewModel;
    }

    private Boolean isOwner(String userName, Long id) {

        Optional<ShoeEntity> offerOpt = shoeRepository.
                findById(id);
        Optional<UserEntity> caller = userRepository.
                findByEmail(userName);

        if (offerOpt.isEmpty() || caller.isEmpty()) {
            return false;
        } else {
            ShoeEntity shoeEntity = offerOpt.get();

            return isAdmin(caller.get()) ||
                    shoeEntity.getCreator().getUsername().equals(userName);
        }

    }

    private Boolean isAdmin(UserEntity user) {
        return user.
                getRoles().
                stream().
                map(UserRoleEntity::getRole).
                anyMatch(r -> r == UserRoleEnum.ADMIN);
    }
}
