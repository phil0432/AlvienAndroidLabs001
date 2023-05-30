package algonquin.cst2335.phil0432.data;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    public MutableLiveData<String> editString = new MutableLiveData<>();
    public MutableLiveData<Boolean> check_coffee = new MutableLiveData<>();
}
