package express.deli.cosema.deliexpressapp.api;

import express.deli.cosema.deliexpressapp.api.response.DefaultResponse;
import express.deli.cosema.deliexpressapp.api.response.ListaSeccionResponse;
import express.deli.cosema.deliexpressapp.api.response.ListaRestauranteResponse;
import express.deli.cosema.deliexpressapp.api.response.MostrarHorarioResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {

    /*Este metodo valida la sesion si Existe el usuario ,
     * si no Existe la creara el registro- Segun Sesion Iniciada*/
    @FormUrlEncoded
    @POST("ValidarUsuarioSesionApi.php")
    Call<DefaultResponse> validarSesionUsuario(@Field("user_uid") String user_uid,
                                               @Field("tipo_usuario") String tipo_usuario,
                                               @Field("display_nombre") String display_nombre,
                                               @Field("foto_user") String foto_user,
                                               @Field("usu_email") String usu_email);
    /*Traendo Seccion de Restaurante*/
    @GET("ListaSeccionApi.php")
    Call<ListaSeccionResponse> mostrarListaSeccion();

    /*Traendo Lista Seccion segun Restaurante*/
    @GET("ListaRestauranteApi.php")
    Call<ListaRestauranteResponse> mostrarListaSecRestaurante(@Query("seccion_id") int seccion_id);

    /*Mostrar Horarios*/
    @GET("MostrarHorarioApi.php")
    Call<MostrarHorarioResponse> mostrarHorario(@Query("restaurante_id") String seccion_id);

    /*Validar Estado del usuario cuando inicia*/
    @FormUrlEncoded
    @POST("ValidaUsuarioRolApi.php")
    Call<DefaultResponse> validarRolUsuario(@Field("user_uid") String user_uid);



}
