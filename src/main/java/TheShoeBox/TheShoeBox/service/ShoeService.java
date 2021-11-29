package TheShoeBox.TheShoeBox.service;

import TheShoeBox.TheShoeBox.model.bindng.ShoeBindingModel;
import TheShoeBox.TheShoeBox.model.service.ShoeServiceModel;
import TheShoeBox.TheShoeBox.model.view.ShoeViewModel;

import java.util.List;

public interface ShoeService {
    ShoeServiceModel addShoe(ShoeBindingModel shoeServiceModel, String userIdentifier);

    List<ShoeViewModel> findAllShoes();
}
