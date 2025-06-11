//Q1) 수학 과목에서 90점 이상인 학생의 이름과 해당 과목 성적 출력
db.students.find(
    {
        "grades":{
            "$elemMatch":{
                "subject":"수학",
                "score":{"$gte":90}
            }
        }
    },
    {
        "name":1,
        "grades.$":1, //elemMatch로 찾은 첫번째 일치하는 배열 요소만 포함
        "_id":0
    }
).forEach(element=>{
    print(element);
});
