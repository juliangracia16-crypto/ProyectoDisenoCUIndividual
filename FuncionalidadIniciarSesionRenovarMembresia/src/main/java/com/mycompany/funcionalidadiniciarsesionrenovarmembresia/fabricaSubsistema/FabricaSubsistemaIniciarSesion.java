
package com.mycompany.funcionalidadiniciarsesionrenovarmembresia.fabricaSubsistema;

import com.mycompany.fitlifegym_negocio.ILoginBO;
import com.mycompany.fitlifegym_negocio.IMembresiaBO;
import com.mycompany.fitlifegym_negocio.IRenovarMembresiaBO;
import com.mycompany.fitlifegym_negocio.fabricaBO.FabricaBO;
import com.mycompany.funcionalidadiniciarsesionrenovarmembresia.FuncionalidadIniciarSesionRenovarMembresia;
import com.mycompany.funcionalidadiniciarsesionrenovarmembresia.IFuncionalidadIniciarSesionRenovarMembresia;

/**
 *
 * @author Julian
 */
public class FabricaSubsistemaIniciarSesion {
    public static IFuncionalidadIniciarSesionRenovarMembresia crearSubsistemaIniciarSesion(){
        ILoginBO loginBO = FabricaBO.crearLoginBO();
        IMembresiaBO membresiaBO = FabricaBO.crearMembresiaBO();
        IRenovarMembresiaBO renovarMembresiaBO = FabricaBO.crearRenovarMembresiaBO();
        return new FuncionalidadIniciarSesionRenovarMembresia(loginBO,membresiaBO, renovarMembresiaBO);
    }
}
