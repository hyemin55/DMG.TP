package pattern.insertPurchaseProduct;

import domain.Manufacturer;
import pattern.InsertOrderState;
import pattern.State;
import pattern.SwitchContext;
import repository.ManufacturerRepository;
import repository.WholesalerRepository;

public class SelectManufacturerState implements State {
    private InsertOrderState parentState;

    public SelectManufacturerState(InsertOrderState parentState) {
        this.parentState = parentState;
    }

    @Override
    public void handle(SwitchContext context, int input) {
        parentState.setM_id(input);
        context.setState(parentState);
    }

    @Override
    public void displayOptions() {
        System.out.println("생산업체 직원 목록");
        ManufacturerRepository manufacturerRepository = new ManufacturerRepository();
        manufacturerRepository.selectManufacturer();
        System.out.println("직원 ID를 선택하세요: ");
    }
}
