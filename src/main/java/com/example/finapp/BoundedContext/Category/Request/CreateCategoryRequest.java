package com.example.finapp.BoundedContext.Category.Request;

/**
 * The CreateCategoryRequest class is used to encapsulate the data required to create a new category.
 * It contains the name and type of the category to be created.
 */
public class CreateCategoryRequest {

    private String name;  // The name of the category to be created
    private String type;  // The type of the category to be created

    /**
     * Constructs a new CreateCategoryRequest with the specified name and type.
     *
     * @param name the name of the category.
     * @param type the type of the category.
     */
    public CreateCategoryRequest(String name, String type) {
        this.name = name;
        this.type = type;
    }

    /**
     * Gets the name of the category.
     *
     * @return the name of the category.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the category.
     *
     * @param name the new name of the category.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the type of the category.
     *
     * @return the type of the category.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of the category.
     *
     * @param type the new type of the category.
     */
    public void setType(String type) {
        this.type = type;
    }
}
