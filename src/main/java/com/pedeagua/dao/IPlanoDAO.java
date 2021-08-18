package com.pedeagua.dao;
import java.util.List;

import com.pedeagua.entity.Plano;
public interface IPlanoDAO {
    List<Plano> getAllPlanos();
    Plano getPlanoById(int cod_plano);
    Plano getPlanoByHash(String hash);
    long addPlano(Plano plano);
    void updatePlano(Plano plano);
    void deletePlano(int cod_plano);
    boolean planoExists(String hash);
}
 