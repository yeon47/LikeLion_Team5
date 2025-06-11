print("--Q1)  모든 제품 중에서 가장 높은 가격을 찾아서 리턴하자");
 db.product.aggregate([
  { $group: { _id: null, 최고가격: { $max: "$price" } } },
  { $project: { _id: 0, 최고가격: 1 } }
])

print("--Q2)  가격이 100보다 큰 상품의 개수를 출력 하자");
 db.product.aggregate([
  { $match: { price: { $gte: 100 } } },
  { $count: "count" }  
])

print("--Q3) name이 문자 S로 시작하는 제품의 총 가격을 계산하자.");
db.product.aggregate([
    {$match:{name:/^s/}},
    {$group:{_id:null, 총합:{$sum:"$price"}}},
    {$project:{_id:0,총합:1}}
])

print("--Q4) Category의 material의 평균 가격을 출력하자");
db.product.aggregate([
    {$match:{category:"material"}},
    {$group:{_id:null, 평균가격:{$avg:"$price"}}},
    {$project:{_id:0, 평균가격:1}}
])
print("--Q5) material 있는 모든 제품의 총 가격을 계산하되 가격이 50보다 큰 제품만 포함한다. 또한 제품 가격이 150보다 큰 경우 총 가격에 10% 할인을 포함한다."); 
// ➔ $match, $group $sum $cond( $gt $multiply) 사용
// $gt: greater than
db.product.aggregate([
    {$match:{category:"material",price:{$lte:50}}},
    {$group:{_id:null, 
        총가격:{
            $sum:{
                $cond:{
                    if:{$gt:["$price",150]},
                    then:{$multiply:["$price",0.9]},
                    else:"$price"
                }  
        }}}
    },
    {$project:{_id:0,총가격:1}}
])