 db.emp.aggregate([
// 1. 조건 필터링: comm이 ""이거나 sal이 2000 이상인 경우
 {
    $match: {
          $or: [
                { eomm: {$eq: "" } },
                {sal: {$gte:2000 } } 
                ] 
           }
  },
// 2. 출력 필드 설정: ename, sal, comm (comm이 null 또는 ""이면 '없음'으로 표시)
{ 
   $project:{
   _id:0,
   ename:1,
   sal:1,
   comm: {
         $cond : { 
                    if :{$eq :["$comm",""]}, 
                    then:"없음",else:"$comm" }
             }
          }
    }
 ]).forEach(element => {
    print(element.ename+","+element.sal+","+element.comm)
 });
