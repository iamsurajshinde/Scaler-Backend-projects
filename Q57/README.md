# Optimizing Retrieval of E-Commerce Entities

## Requirements

You are provided with few model classes, Fields have been already added in each model.

You need to add Cardinalities, make sure relevant tables are created and complete implementation of 2 methods present in `CategoryService`
 - `getNamesOfAllCategoriesAndLinkedProductsAndTheirImages` - We want to capture titles of all categories, names of products associated with those categories and descriptiveNames of all images associated with a product and return these as `List<String>`. Order doesn't matter.
 -  `getNamesOfAllCategoriesAndTheirSubCategories` - We want to capture titles of all categories, names of all subCategories associated with these categories and return these as `List<String>`. Order doesn't matter.
 - You need to make sure that `getNamesOfAllCategoriesAndLinkedProductsAndTheirImages` is able to retrive all the data from Database in most efficient way with minimum number of queries.
 - You also need to make sure that `getNamesOfAllCategoriesAndTheirSubCategories` is able to get data in batches of size 3.

You also need to use JpaRepository in all repositories present in this assignment.

## Hints
- Nothing is needed from your side in pom.xml or application.properties
- No new file need to be created, No field need to be added/modified/removed.
- If you will try to run testcases without giving solution, all Testcases will fail.
- Please don't change anything in file `CustomStatementInspector`