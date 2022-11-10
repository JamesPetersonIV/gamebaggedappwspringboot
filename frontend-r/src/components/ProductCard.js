import React from 'react';

function ProductCard(props) {
    let product = props.product;
    let category = props.category;

    let altText = product.name + " " + product.description;
    return(
        <div className="col mb-3" key={product["primary_isbn13"]}>
            <div className="card h-100  position-relative">
                <img src={product[""]} className="card-img-top book-img mx-auto pt-1" alt={altText}  />
                <div className="card-body book-card-details">
                    {
                        category === "category" ? <h5 className="card-title on-list">{product["weeks_on_list"]} WEEKS ON LIST</h5> : ""
                    }

                    <h5 className="card-title book-title">{product.name} &nbsp;&nbsp;
                        <span className="badge position-absolute top-0 start-0 rounded-pill translate-middle bg-primary">{product.category}</span>
                    </h5>
                    <h6 className="card-subtitle mb-2 text-muted book-author"><i>{product.description}</i></h6>
                    <p className="card-text">{product.description}</p>
                    <a target="_blank" rel="noreferrer" href={""} className="btn btn-secondary buy">BUY</a>
                </div>
            </div>
        </div>
    );

}

export default ProductCard;