package TheShoeBox.TheShoeBox.service.impl;

import TheShoeBox.TheShoeBox.model.entity.UserEntity;
import TheShoeBox.TheShoeBox.model.entity.UserRoleEntity;
import TheShoeBox.TheShoeBox.model.entity.enums.UserRoleEnum;
import TheShoeBox.TheShoeBox.model.service.UserServiceModel;
import TheShoeBox.TheShoeBox.repository.RoleRepository;
import TheShoeBox.TheShoeBox.repository.UserRepository;
import TheShoeBox.TheShoeBox.service.UserEntityService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserEntityServiceImpl implements UserEntityService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final ShoeBoxUserDetails shoeBoxUserDetails;
    //private final CloudinaryService cloudinaryService;
//    private final PictureRepository pictureRepository;

    public UserEntityServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, ModelMapper modelMapper, ShoeBoxUserDetails shoeBoxUserDetails) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.shoeBoxUserDetails = shoeBoxUserDetails;
    }

    @Override
    public void initUsers() {

//        if (userRepository.count() > 0) {
//            return;
//        }
//
//        UserRoleEntity adminRole = roleRepository.findByRole(UserRoleEnum.ADMIN);
//        UserRoleEntity userRole = roleRepository.findByRole(UserRoleEnum.USER);
//
//        UserEntity admin = new UserEntity();
//        admin
//                .setUsername("admin")
//                .setFirstName("Admin")
//                .setLastName("Adminov")
//                .setEmail("admin@abv.bg")
//                .setPassword(passwordEncoder.encode("admin"));
//
//        admin.setRoles(List.of(adminRole, userRole));
//        userRepository.save(admin);
//
//        UserEntity user = new UserEntity();
//        user.setUsername("pesho")
//                .setFirstName("Petyr")
//                .setLastName("Petrov")
//                .setEmail("peshko@mail.bg")
//                .setPassword(passwordEncoder.encode("12345"));
//
//
//        user.setRoles(List.of(userRole));
//        userRepository.save(user);
    }

    @Override
    public UserServiceModel findById(Long id) {
        return userRepository.findById(id)
                .map(user -> modelMapper.map(user, UserServiceModel.class))
                .orElse(null);
    }

    @Override
    public Optional<UserEntity> findUserByEmail(String username) {
        return userRepository.findByEmail(username);
    }

    @Override
    public UserServiceModel findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(user -> modelMapper.map(user, UserServiceModel.class))
                .orElse(null);
    }

    @Override
    public void registerUser(UserServiceModel userServiceModel){

        UserRoleEntity userRole = roleRepository.findByRole(UserRoleEnum.USER);

        UserEntity newUser = new UserEntity();

        newUser.
                setUsername(userServiceModel.getUsername()).
                setFirstName(userServiceModel.getFirstname()).
                setLastName(userServiceModel.getLastname()).
                setEmail(userServiceModel.getEmail()).
                setPassword(passwordEncoder.encode(userServiceModel.getPassword())).
                setRoles(List.of(userRole));

        newUser = userRepository.save(newUser);

        UserDetails principal = shoeBoxUserDetails.loadUserByUsername(newUser.getUsername());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principal,
                newUser.getPassword(),
                principal.getAuthorities()
        );

        SecurityContextHolder.
                getContext().
                setAuthentication(authentication);

    }

    private boolean existByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean isUserNameFree(String username) {
        return userRepository.findByUsernameIgnoreCase(username).isEmpty();
    }
}
