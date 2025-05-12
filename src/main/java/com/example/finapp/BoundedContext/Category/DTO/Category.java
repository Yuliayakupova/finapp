package com.example.finapp.BoundedContext.Category.DTO;

/**
 * The Category class represents a category in the personal finance application.
 * It holds details about the category such as its unique identifier (id), name, and type.
 */
public class Category {

    // The unique identifier for the category.
    private int id;

    // The name of the category (e.g., "Food", "Entertainment").
    private String name;

    // The type of the category (e.g., "Income", "Expense").
    private String type;

    /**
     * Constructor for creating a new Category object with specified id, name, and type.
     *
     * @param id The unique identifier of the category.
     * @param name The name of the category.
     * @param type The type of the category.
     */
    public Category(int id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public Category() {

    }

    /**
     * Gets the unique identifier of the category.
     *
     * @return The unique id of the category.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the category.
     *
     * @param id The unique identifier to set for the category.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name of the category.
     *
     * @return The name of the category.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the category.
     *
     * @param name The name to set for the category.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the type of the category.
     *
     * @return The type of the category (e.g., "Income" or "Expense").
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of the category.
     *
     * @param type The type to set for the category.
     */
    public void setType(String type) {
        this.type = type;
    }
}
