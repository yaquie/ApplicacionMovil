package express.deli.cosema.deliexpressapp.restaurante.detalles.useCase;

import express.deli.cosema.deliexpressapp.base.UseCase;
import express.deli.cosema.deliexpressapp.restaurante.detalles.source.DetallesRepository;
import express.deli.cosema.deliexpressapp.restaurante.detalles.source.DetallesSource;

public class MostrarHorario extends UseCase<MostrarHorario.RequestValues, MostrarHorario.ResponseValue> {

    private DetallesRepository repository;

    public MostrarHorario(DetallesRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(final RequestValues requestValues) {
        repository.onMostrarHorario(requestValues.getCodigoRestaurante(), new DetallesSource.CallBackResultado() {
            @Override
            public void onCallBackResultado(String horarioGeneral, String lunesResp, String martesResp, String miercolesResp, String juevesResp, String viernesResp, String sabadoResp, String domingoResp) {
                getUseCaseCallback().onSuccess(new ResponseValue(horarioGeneral, lunesResp, martesResp, miercolesResp, juevesResp, viernesResp, sabadoResp, domingoResp));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private String codigoRestaurante;

        public RequestValues(String codigoRestaurante) {
            this.codigoRestaurante = codigoRestaurante;
        }

        public String getCodigoRestaurante() {
            return codigoRestaurante;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private String horarioGeneral;
        private String lunesResp;
        private String martesResp;
        private String miercolesResp;
        private String juevesResp;
        private String viernesResp;
        private String sabadoResp;
        private String domingoResp;

        public ResponseValue(String horarioGeneral, String lunesResp, String martesResp, String miercolesResp, String juevesResp, String viernesResp, String sabadoResp, String domingoResp) {
            this.horarioGeneral = horarioGeneral;
            this.lunesResp = lunesResp;
            this.martesResp = martesResp;
            this.miercolesResp = miercolesResp;
            this.juevesResp = juevesResp;
            this.viernesResp = viernesResp;
            this.sabadoResp = sabadoResp;
            this.domingoResp = domingoResp;
        }

        public String getHorarioGeneral() {
            return horarioGeneral;
        }

        public String getLunesResp() {
            return lunesResp;
        }

        public String getMartesResp() {
            return martesResp;
        }

        public String getMiercolesResp() {
            return miercolesResp;
        }

        public String getJuevesResp() {
            return juevesResp;
        }

        public String getViernesResp() {
            return viernesResp;
        }

        public String getSabadoResp() {
            return sabadoResp;
        }

        public String getDomingoResp() {
            return domingoResp;
        }
    }
}
