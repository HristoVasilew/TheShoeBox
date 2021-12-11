package TheShoeBox.TheShoeBox.service.impl;

import TheShoeBox.TheShoeBox.exeptions.ObjectNotFoundException;
import TheShoeBox.TheShoeBox.exeptions.UserNotLoggedInException;
import TheShoeBox.TheShoeBox.model.bindng.ShoeBindingModel;
import TheShoeBox.TheShoeBox.model.entity.*;
import TheShoeBox.TheShoeBox.model.entity.enums.ConditionEnum;
import TheShoeBox.TheShoeBox.model.entity.enums.ShoeCategoryEnum;
import TheShoeBox.TheShoeBox.model.entity.enums.UserRoleEnum;
import TheShoeBox.TheShoeBox.model.service.ShoeServiceModel;
import TheShoeBox.TheShoeBox.model.service.ShoeUpdateServiceModel;
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

import java.math.BigDecimal;
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
        shoe.setOrdered(false);
        ShoeEntity savedShoe = shoeRepository.save(shoe);

        return modelMapper.map(savedShoe, ShoeServiceModel.class);
    }

    @Override
    public List<ShoeViewModel> findAllShoes() {
        return shoeRepository.findAll()
                .stream().map(s -> modelMapper.map(s, ShoeViewModel.class))
                .collect(Collectors.toList());

    }

    @Override
    public ShoeViewModel findByIdAndName(Long id, String name) {
        return shoeRepository.findById(id)
                .map(s -> mapDetailsView(name, s))
                .get();
    }

    @Override
    public ShoeViewModel findById(Long id) {
        ShoeEntity shoe = shoeRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Shoe with id " + id + " not found!"));

        return modelMapper.map(shoe, ShoeViewModel.class);
    }

    @Override
    public void deleteOffer(Long id) {
        shoeRepository.deleteById(id);
    }

    @Override
    public void updateOffer(ShoeUpdateServiceModel offerModel) {

        ShoeEntity offerEntity =
                shoeRepository.findById(offerModel.getId()).orElseThrow(() ->
                        new ObjectNotFoundException("Offer with id " + offerModel.getId() + " not found!"));

        offerEntity
                .setBrand(offerModel.getBrand())
                .setModel(offerModel.getModel())
                .setPrice(offerModel.getPrice())
                .setSize(offerModel.getSize())
                .setDescription(offerModel.getDescription())
                .setLocation(offerModel.getLocation())
                .setShoeConditionEntity(shoeConditionRepository.findShoeConditionEntityByName(offerModel.getConditionEnum()))
                .setImageUrl(offerModel.getImageUrl())
                .setShoeCategoryEntity(categoryRepository.findShoeCategoryEntityByName(offerModel.getShoeCategoryEnum()))
                .setCreatedOn(LocalDateTime.now());

        shoeRepository.save(offerEntity);
    }

    private ShoeViewModel mapDetailsView(String currentUser, ShoeEntity offer) {
        ShoeViewModel shoeViewModel = this.modelMapper.map(offer, ShoeViewModel.class);
        shoeViewModel.setCanDelete(isOwner(currentUser, offer.getId()));
        shoeViewModel.setModel(offer.getModel());
        shoeViewModel.setBrand(offer.getBrand());
        shoeViewModel.setConditionEnum(ConditionEnum.valueOf(offer.getShoeConditionEntity().getName().name()));
        shoeViewModel.setShoeCategoryEnum(offer.getShoeCategoryEntity().getName());
        shoeViewModel.setSellerFullName(
                offer.getCreator().getFirstName() + " " + offer.getCreator().getLastName());
        return shoeViewModel;
    }

    @Override
    public boolean isOwner(String userName, Long id) {

        Optional<ShoeEntity> offerOpt = shoeRepository.
                findById(id);
        Optional<UserEntity> caller = userRepository.
                findByEmail(userName);

        if (offerOpt.isEmpty() || caller.isEmpty()) {
            throw new UserNotLoggedInException("You must be logged in.");
        } else {
            ShoeEntity shoeEntity = offerOpt.get();

            return isAdmin(caller.get()) ||
                    shoeEntity.getCreator().getEmail().equals(userName);
        }

    }

    @Override
    public void save(ShoeEntity shoe) {
        shoeRepository.save(shoe);
    }

    @Override
    public ShoeEntity findShoeById(Long productId) {
        return shoeRepository.findById(productId)
                .orElseThrow(() -> new ObjectNotFoundException("Shoe with id " + productId + " not found!"));
    }

    @Override
    public void initShoes() {
        if (shoeRepository.count() == 0) {

            ShoeEntity shoe1 = new ShoeEntity();
            shoe1.setId(1L);
            shoe1
                    .setImageUrl("https://images.vans.com/is/image/Vans/KRDJZ8-HERO?$583x583$")
                    .setBrand("Vans")
                    .setModel("X USPS® AUTHENTIC")
                    .setSize(new BigDecimal(40))
                    .setShoeConditionEntity(shoeConditionRepository.findShoeConditionEntityByName(ConditionEnum.BrandNew))
                    .setShoeCategoryEntity(categoryRepository.findShoeCategoryEntityByName(ShoeCategoryEnum.Canvas))
                    .setLocation("Orlando, United States")
                    .setDescription("The Sk8-Hi, Vans legendary lace-up high-top built for skating, has an Off the Wall persona thanks to the iconic leather sidestripe")
                    .setPrice(new BigDecimal(249))
                    .setCreator(userRepository.getById(1L))
                    .setCreatedOn(LocalDateTime.now())
                    .setOrdered(false);

            shoeRepository.save(shoe1);

            ShoeEntity shoe2 = new ShoeEntity();
            shoe2.setId(2L);
            shoe2
                    .setImageUrl("https://images.vans.com/is/image/Vans/VC0836-HERO?$CUSTOMS-PDP-LARGE$")
                    .setBrand("Vans")
                    .setModel("Old School")
                    .setSize(new BigDecimal(43))
                    .setShoeConditionEntity(shoeConditionRepository.findShoeConditionEntityByName(ConditionEnum.Used))
                    .setShoeCategoryEntity(categoryRepository.findShoeCategoryEntityByName(ShoeCategoryEnum.Athletic))
                    .setLocation("Busan, South Korea")
                    .setDescription("x USPS® Authentic comes equipped with metal eyelets, signature rubber waffle outsoles, and a reflective flag label.")
                    .setPrice(new BigDecimal(149))
                    .setCreator(userRepository.getById(1L))
                    .setCreatedOn(LocalDateTime.now())
                    .setOrdered(false);

            shoeRepository.save(shoe2);


            ShoeEntity shoe3 = new ShoeEntity();
            shoe3.setId(3L);
            shoe3
                    .setImageUrl("https://images.vans.com/is/image/Vans/VC0847-HERO?$CUSTOMS-PDP-LARGE$")
                    .setBrand("Vans")
                    .setModel("Old School vol ||")
                    .setSize(new BigDecimal(38))
                    .setShoeConditionEntity(shoeConditionRepository.findShoeConditionEntityByName(ConditionEnum.BrandNew))
                    .setShoeCategoryEntity(categoryRepository.findShoeCategoryEntityByName(ShoeCategoryEnum.Leather))
                    .setLocation("Sao Paolo, London")
                    .setDescription("x USPS® Authentic comes equipped with metal eyelets, signature rubber waffle outsoles, and a reflective flag label.")
                    .setPrice(new BigDecimal(349))
                    .setCreator(userRepository.getById(2L))
                    .setCreatedOn(LocalDateTime.now())
                    .setOrdered(false);

            shoeRepository.save(shoe3);


            ShoeEntity shoe4 = new ShoeEntity();
            shoe4.setId(4L);
            shoe4
                    .setImageUrl("https://images.vans.com/is/image/Vans/VN0A5FCBY28-HERO?hei=1600&wid=1600&qlt=95")
                    .setBrand("Vans")
                    .setModel("Skate")
                    .setSize(new BigDecimal(41))
                    .setShoeConditionEntity(shoeConditionRepository.findShoeConditionEntityByName(ConditionEnum.BrandNew))
                    .setShoeCategoryEntity(categoryRepository.findShoeCategoryEntityByName(ShoeCategoryEnum.HighTopBasketball))
                    .setLocation("Kawasaki, Japan")
                    .setDescription("The USPS® Authentic comes packaged in a custom “Priority Mail®” shoe box")
                    .setPrice(new BigDecimal(269))
                    .setCreator(userRepository.getById(2L))
                    .setCreatedOn(LocalDateTime.now())
                    .setOrdered(false);

            shoeRepository.save(shoe4);


            ShoeEntity shoe5 = new ShoeEntity();
            shoe5.setId(5L);
            shoe5
                    .setImageUrl("https://images.vans.com/is/image/Vans/CS0058-HERO?$CUSTOMS-PDP-LARGE$")
                    .setBrand("Vans")
                    .setModel("Skate vol ||")
                    .setSize(new BigDecimal(41))
                    .setShoeConditionEntity(shoeConditionRepository.findShoeConditionEntityByName(ConditionEnum.BrandNew))
                    .setShoeCategoryEntity(categoryRepository.findShoeCategoryEntityByName(ShoeCategoryEnum.Synthetic))
                    .setLocation("Tornio, Finland")
                    .setDescription("The USPS® Authentic comes packaged in a custom “Priority Mail®” shoe box")
                    .setPrice(new BigDecimal(269))
                    .setCreator(userRepository.getById(2L))
                    .setCreatedOn(LocalDateTime.now())
                    .setOrdered(false);

            shoeRepository.save(shoe5);
        }
    }

    public Boolean isAdmin(UserEntity user) {
        return user.
                getRoles().
                stream().
                map(UserRoleEntity::getRole).
                anyMatch(r -> r == UserRoleEnum.ADMIN);
    }
}
