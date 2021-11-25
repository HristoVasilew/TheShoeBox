package TheShoeBox.TheShoeBox.service.impl;

import TheShoeBox.TheShoeBox.model.entity.UserEntity;
import TheShoeBox.TheShoeBox.model.entity.UserRoleEntity;
import TheShoeBox.TheShoeBox.model.entity.enums.UserRoleEnum;
import TheShoeBox.TheShoeBox.model.service.UserRegisterServiceModel;
import TheShoeBox.TheShoeBox.repository.RoleRepository;
import TheShoeBox.TheShoeBox.repository.UserRepository;
import TheShoeBox.TheShoeBox.service.UserEntityService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@Service
public class UserEntityServiceImpl implements UserEntityService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final UserDetailsImpl userDetailsImpl;
    //private final CloudinaryService cloudinaryService;
//    private final PictureRepository pictureRepository;

    public UserEntityServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, ModelMapper modelMapper, UserDetailsImpl userDetailsImpl) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.userDetailsImpl = userDetailsImpl;
    }

    @Override
    public void initUsers() {

        if (userRepository.count() > 0) {
            return;
        }

        UserRoleEntity adminRole = roleRepository.findByRole(UserRoleEnum.ADMIN).orElse(null);
        UserRoleEntity userRole = roleRepository.findByRole(UserRoleEnum.USER).orElse(null);

        UserEntity admin = new UserEntity();
        admin.setUsername("admin");
        admin.setFirstName("Admin");
        admin.setLastName("Adminov");
        admin.setEmail("admin@abv.bg");
        admin.setPassword(passwordEncoder.encode("admin"));

        admin.setRoles(Set.of(adminRole, userRole));
        userRepository.save(admin);

        UserEntity user = new UserEntity();
        user.setUsername("pesho");
        user.setFirstName("Petyr");
        user.setLastName("Petrov");
        user.setEmail("peshko@mail.bg");
        user.setPassword(passwordEncoder.encode("12345"));


        user.setRoles(Set.of(userRole));
        userRepository.save(user);
    }

    @Override
    public void registerUser(UserRegisterServiceModel userRegisterServiceModel) throws IOException {

        if (existByUsername(userRegisterServiceModel.getUsername())) {
            throw new UsernameNotFoundException("There is an account with that email address: "
                    + userRegisterServiceModel.getUsername());
        }

        UserEntity user = modelMapper.map(userRegisterServiceModel, UserEntity.class);

        user.setPassword(passwordEncoder.encode(userRegisterServiceModel.getPassword()));
        user.setRoles(Set.of(roleRepository.findByRole(UserRoleEnum.USER).orElseThrow()));

        //var picture = createPictureEntity(userRegisterServiceModel.getProfilePictureUrl());

//        pictureRepository.saveAndFlush(picture);

//        user.setProfilePictureUrl(picture);

        userRepository.save(user);

        UserDetails principal = userDetailsImpl.loadUserByUsername(user.getUsername());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principal,
                user.getPassword(),
                principal.getAuthorities()
        );

        SecurityContextHolder.
                getContext().
                setAuthentication(authentication);

    }

    private boolean existByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}
