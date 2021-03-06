package com.mall.shop.entity.gen;

import java.io.Serializable;

public class Specification implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column specification.id
     *
     * @mbggenerated
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column specification.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column specification.sort_order
     *
     * @mbggenerated
     */
    private Integer sortOrder;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table specification
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column specification.id
     *
     * @return the value of specification.id
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column specification.id
     *
     * @param id the value for specification.id
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column specification.name
     *
     * @return the value of specification.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column specification.name
     *
     * @param name the value for specification.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column specification.sort_order
     *
     * @return the value of specification.sort_order
     *
     * @mbggenerated
     */
    public Integer getSortOrder() {
        return sortOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column specification.sort_order
     *
     * @param sortOrder the value for specification.sort_order
     *
     * @mbggenerated
     */
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }
}