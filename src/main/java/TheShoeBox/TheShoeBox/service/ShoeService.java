package TheShoeBox.TheShoeBox.service;

import TheShoeBox.TheShoeBox.model.bindng.ShoeBindingModel;
import TheShoeBox.TheShoeBox.model.entity.ShoeEntity;
import TheShoeBox.TheShoeBox.model.service.ShoeServiceModel;
import TheShoeBox.TheShoeBox.model.view.ShoeViewModel;

import java.util.List;

public interface ShoeService {
    ShoeServiceModel addShoe(ShoeBindingModel shoeServiceModel, String userIdentifier);

    List<ShoeViewModel> findAllShoes();

    ShoeViewModel findById(Long id, String name);

    void deleteOffer(Long id);
}
