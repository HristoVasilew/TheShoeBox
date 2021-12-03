package TheShoeBox.TheShoeBox.service;

import TheShoeBox.TheShoeBox.model.view.StatsView;

public interface StatsService {
    void onRequest();
    StatsView getStats();
}
