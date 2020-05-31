package express.deli.cosema.deliexpressapp.restaurante.useCase;

import java.util.List;

import express.deli.cosema.deliexpressapp.base.UseCase;
import express.deli.cosema.deliexpressapp.listenerDataSource.CallBackResultado1;
import express.deli.cosema.deliexpressapp.restaurante.entidad.SeccionUi;
import express.deli.cosema.deliexpressapp.restaurante.source.RestauranteRepository;

public class MotrarListaRestSec extends UseCase<MotrarListaRestSec.RequestValues, MotrarListaRestSec.ResponseValue> {

    private RestauranteRepository repository;

    public MotrarListaRestSec(RestauranteRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.onMostrarListaRestaurante(new CallBackResultado1<List<SeccionUi>>() {
            @Override
            public void onCallBackResultado(List<SeccionUi> resultado) {
                if (resultado != null) {
                    getUseCaseCallback().onSuccess(new ResponseValue(resultado));
                } else {
                    getUseCaseCallback().onError();
                }
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {

    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private List<SeccionUi> seccionUiList;

        public ResponseValue(List<SeccionUi> seccionUiList) {
            this.seccionUiList = seccionUiList;
        }

        public List<SeccionUi> getSeccionUiList() {
            return seccionUiList;
        }
    }
}
