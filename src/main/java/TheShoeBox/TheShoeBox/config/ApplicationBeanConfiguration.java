package TheShoeBox.TheShoeBox.config;

import com.cloudinary.Cloudinary;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import java.util.Map;

@Configuration
public class ApplicationBeanConfiguration {
//
//    private final CloudinaryConfiguration config;
//
//    public ApplicationBeanConfiguration(CloudinaryConfiguration config) {
//        this.config = config;
//    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Pbkdf2PasswordEncoder();
    }

//    @Bean
//    public Cloudinary cloudinary() {
//        return new Cloudinary(
//                Map.of(
//                        "cloud_name", config.getCloudName(),
//                        "api_key", config.getApiKey(),
//                        "api_secret", config.getApiSecret()
//                )
//        );
//    }


}
