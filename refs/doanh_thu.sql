Select month(s.created_date) as 'Tháng', Sum(sp.price_unit) as 'Doanh thu'
From tbl_saleorder s, tbl_saleorder_products sp
where s.id = sp.saleorder_id
Group by month(s.created_date)
