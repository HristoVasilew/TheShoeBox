package TheShoeBox.TheShoeBox.service;

import TheShoeBox.TheShoeBox.model.bindng.ShoeBindingModel;
import TheShoeBox.TheShoeBox.model.entity.ShoeEntity;
import TheShoeBox.TheShoeBox.model.service.ShoeServiceModel;
import TheShoeBox.TheShoeBox.model.service.ShoeUpdateServiceModel;
import TheShoeBox.TheShoeBox.model.view.ShoeViewModel;

import java.util.List;

public interface ShoeService {
    ShoeServiceModel addShoe(ShoeBindingModel shoeServiceModel, String userIdentifier);

    List<ShoeViewModel> findAllShoes();

    ShoeViewModel findByIdAndName(Long id, String name);

    ShoeViewModel findById(Long id);

    void deleteOffer(Long id);

    void updateOffer(ShoeUpdateServiceModel serviceModel);

    boolean isOwner(String userName, Long id);
}
