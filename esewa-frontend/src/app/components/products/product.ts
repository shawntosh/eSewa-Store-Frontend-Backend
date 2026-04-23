export interface IProduct{
    pid?: number;
    name?: string;
    price?: number;
    quantity?: number;
    description?: string;
    productImage?:string;
    category?: Category;
    customer?: Customer;

}

export interface Category{
    categoryId: number;
}
export interface Customer{
    customerId: number;
}
