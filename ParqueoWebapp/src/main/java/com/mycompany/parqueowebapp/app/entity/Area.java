/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parqueowebapp.app.entity;

import java.io.Serializable;
import java.util.Collection;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 *
 * @author daniloues
 */

@Entity
@Table(name = "area", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Area.findAreaHasParent", query = "SELECT a.idAreaPadre FROM Area a WHERE a.idArea = :idArea"),
    @NamedQuery(name = "Area.findAll", query = "SELECT a FROM Area a"),
    @NamedQuery(name = "Area.findByIdPadre", query = "SELECT a FROM Area a WHERE a.idAreaPadre.idArea = :idAreaPadre ORDER BY a.nombre ASC"),
    @NamedQuery(name = "Area.countByIdPadre", query = "SELECT COUNT(a.idArea) FROM Area a WHERE a.idAreaPadre.idArea = :idAreaPadre"),
    @NamedQuery(name = "Area.findRaices", query = "SELECT a FROM Area a WHERE a.idAreaPadre IS NULL ORDER BY a.nombre ASC"),
    @NamedQuery(name = "Area.findNombresPadres", query = "SELECT a.nombre FROM Area a WHERE a.idAreaPadre.idArea = 1")
})

public class Area implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_area")
    private Integer idArea;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "idAreaPadre")
    private Collection<Area> areaCollection;
    @JoinColumn(name = "id_area_padre", referencedColumnName = "id_area")
    @ManyToOne
    private Area idAreaPadre;
    @OneToMany(mappedBy = "idArea")
    private Collection<Espacio> espacioCollection;

    public Area() {
    }

    public Area(Integer idArea) {
        this.idArea = idArea;
    }

    public Integer getIdArea() {
        return idArea;
    }

    public void setIdArea(Integer idArea) {
        this.idArea = idArea;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Collection<Area> getAreaCollection() {
        return areaCollection;
    }

    public void setAreaCollection(Collection<Area> areaCollection) {
        this.areaCollection = areaCollection;
    }

    public Area getIdAreaPadre() {
        return idAreaPadre;
    }

    public void setIdAreaPadre(Area idAreaPadre) {
        this.idAreaPadre = idAreaPadre;
    }

    public Collection<Espacio> getEspacioCollection() {
        return espacioCollection;
    }

    public void setEspacioCollection(Collection<Espacio> espacioCollection) {
        this.espacioCollection = espacioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idArea != null ? idArea.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Area)) {
            return false;
        }
        Area other = (Area) object;
        if ((this.idArea == null && other.idArea != null) || (this.idArea != null && !this.idArea.equals(other.idArea))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.parqueowebapp.Area[ idArea=" + idArea + " ]";
    }
    
}
