package com.example.finapp.BoundedContext.Category.Request;

/**
 * The UpdateCategoryRequest class is used to encapsulate the data required to update an existing category.
 * It contains the name and type of the category to be updated.
 */
public class UpdateCategoryRequest {

    private String name;  // The new name of the category to be updated
    private String type;  // The new type of the category to be updated

    /**
     * Constructs a new UpdateCategoryRequest with the specified name and type.
     *
     * @param name the new name of the category.
     * @param type the new type of the category.
     */
    public UpdateCategoryRequest(String name, String type) {
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
