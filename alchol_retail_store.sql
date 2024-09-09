use alcohol_retail_store;


create table `product` (
	`p_id` int auto_increment primary key,
	`p_brand` varchar(100) NOT NULL,
	`p_kind` char(1) NOT NULL COMMENT '1:위스키, 2:럼, 3:와인, 4:증류주, 5:맥주, 6:사케',
	`p_name` varchar(100) NOT NULL,
	`p_country` varchar(100) NOT NULL,
	`pp_capacity` INT NOT NULL,
    `pp_etc` TEXT COMMENT '제품상세설명'
);

create table `manufacturer` (
	`m_id`	int auto_increment	primary key,
	`m_businessID`	varchar(30)	NOT NULL,
	`m_phone`	varchar(20)	NOT NULL,
	`m_department`	varchar(30)	NULL,
	`m_jobTitle`	varchar(30)	NULL,
	`m_adress`	varchar(100)	NOT NULL,
	`m_name`	varchar(30)	NOT NULL,
	`m_person`	varchar(20)	NOT NULL
);

create table `wholesaler` (
	`w_id`	int auto_increment	primary key,
	`w_department`	varchar(30)	NOT NULL,
	`w_name`	varchar(20)	NOT NULL,
	`w_phone`	varchar(20)	NOT NULL,
	`w_jobTitle`	varchar(30)	NOT NULL
);

CREATE TABLE `purchaseProduct` (
    `pp_id` INT AUTO_INCREMENT PRIMARY KEY,
    `pp_orderDate` DATE NOT NULL,
    `pp_receivedDate` DATE NOT NULL,
    `pp_costPrice` INT NOT NULL,
    `pp_sellingPrice` INT NOT NULL COMMENT '상품입고가격*1.3',
    `pp_receivedCount` INT NOT NULL,
    `pp_manufactureDate` DATE NOT NULL,
    `pp_expirationDate` DATE DEFAULT NULL, -- 기본값으로 '-' 대신 NULL 사용
    `m_id` INT, -- 컬럼 타입을 명시해야 함
    `w_id` INT, -- 컬럼 타입을 명시해야 함
    `p_id` INT, -- 컬럼 타입을 명시해야 함
    FOREIGN KEY (`m_id`) REFERENCES `manufacturer`(`m_id`),
    FOREIGN KEY (`w_id`) REFERENCES `wholesaler`(`w_id`),
    FOREIGN KEY (`p_id`) REFERENCES `product`(`p_id`)
);

create view selectPurchaseProduct as
select pp.pp_id, pp.pp_orderDate, pp.pp_receivedDate, m.m_name, w.w_name, p.p_kind, p.p_brand, p.p_capacity, pp.pp_costPrice, p.p_name, pp.pp_receivedCount
from manufacturer m, product p, purchaseProduct pp, wholesaler w
where m.m_id = pp.m_id and p.p_id = pp.p_id and w.w_id = pp.w_id
order by pp.pp_id;