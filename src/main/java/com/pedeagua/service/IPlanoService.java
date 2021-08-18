package com.pedeagua.service;
import java.util.List;

import com.pedeagua.entity.Plano;
public interface IPlanoService {
    List<Plano> getAllPlanos();
    Plano getPlanoById(int cod_plano);
    Plano getPlanoByHash(String hash);
    long addPlano(Plano plano);
    void updatePlano(Plano plano);
    void deletePlano(int cod_plano);
}
 