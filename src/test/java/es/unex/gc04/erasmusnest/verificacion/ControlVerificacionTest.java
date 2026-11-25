package es.unex.gc04.erasmusnest.verificacion;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import es.unex.gc04.erasmusnest.usuario.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ExtendedModelMap;

/** Bloqueo de publicar y contactar sin verificar (TK-007, RN-02). Cuentas sintéticas (NFR-009). */
class ControlVerificacionTest {

    private final ControlVerificacion control = new ControlVerificacion();

    @Test
    void unaCuentaNoVerificadaNoPuedePublicarNiContactar() {
        Usuario pendiente = new Usuario("ana.garcia@alumnos.unex.es", "hash-sintetico");

        assertThat(control.puedePublicarYContactar(pendiente)).isFalse();
        assertThatThrownBy(() -> control.exigirCuentaVerificada(pendiente))
                .isInstanceOf(CuentaNoVerificadaException.class)
                .hasMessage(CuentaNoVerificadaException.MOTIVO);
    }

    @Test
    void unaCuentaVerificadaSiPuede() {
        Usuario verificada = new Usuario("luis.perez@alumnos.unex.es", "hash-sintetico");
        verificada.verificar();

        assertThat(control.puedePublicarYContactar(verificada)).isTrue();
        assertThatCode(() -> control.exigirCuentaVerificada(verificada)).doesNotThrowAnyException();
    }

    @Test
    void laPantallaExplicaPorQueEstaBloqueada() {
        ExtendedModelMap model = new ExtendedModelMap();

        String vista = new CuentaPendienteController().cuentaPendiente(model);

        assertThat(vista).isEqualTo("cuenta-pendiente");
        assertThat(model).containsEntry("motivo", CuentaNoVerificadaException.MOTIVO);
    }

    @Test
    void siUnaAccionLanzaElBloqueoSeMuestraLaPantallaConElMotivo() {
        ExtendedModelMap model = new ExtendedModelMap();

        String vista = new CuentaPendienteController.ManejadorCuentaNoVerificada()
                .cuentaNoVerificada(new CuentaNoVerificadaException(), model);

        assertThat(vista).isEqualTo("cuenta-pendiente");
        assertThat(model).containsEntry("motivo", CuentaNoVerificadaException.MOTIVO);
    }
}
