package com.myresume.web.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myresume.web.app.entities.Resume;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, String> {

	@Query("SELECT a from Resume a, IN(a.skills) t WHERE a.removed IS NULL AND t.name = :name ORDER BY a.user.name")
	public List<Resume> searchBySkills(@Param("name") String name);
	
//	@Query("SELECT new com.myresume.web.app.models.ResumeModel(u.id, u.direccion, u.fechaReserva, u.usuario.id, u.pago.monto, u.usuario.nombre, u.usuario.telefono, u, u.estadoPago, u.estadoSolicitud, u.codigoDescuento.id, u.transaccion, u.link, u.modo, u.desde, u.hasta, u.alta, u.baja, u.modificacion, u.ciudad, u.finalizacion, u.puntualidad, u.atencion, u.observacion, u.calidad, u.motivoBaja.id, u.motivoBaja.motivo, u.motivoBaja.descripcion, u.calificacionLavador) from Solicitud u WHERE u.id = :id AND u.baja IS NULL")
//	public ResumeModel searchResumeModel(@Param("id") String id);
	
}
