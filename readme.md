this is a spring demo use spring and springmvc framework, will add mybatis in the near future
also I add some tests class like thread, aop, annotation, cache and so on..
keep learning, keep happy!
hello world!!

git use tips
 we often work on a new branch, but sometimes we will get some trouble when we merge the branch changes to the remote master
 for some files conflics , that's really bad, so how to forbide this issue? please follow the tips bellow

 1. checkout back to your local master
 2. use git pull to update your code keeping same as remote master
 3. checkout back to your working branch
 4. use merge command 'git merge master' to update your working branch to keep same as remote master

 after you doing these, you can commit and push your change free.

2020/9/21 add spring bean test, use @Component and <bean/> label to let spring Ioc create bean,we can use this way to
create two(or more) beans with different property.

2020/9/22 add lock test (synchronized and lock) to implement thread safe
2020/10/16 test spring collection and map autowired
2020/10/17 add JDK dynamic proxy demo
2020/10/19 add template method dessign pattern demo
2020/10/19 add oberver dessign pattern demo(implement by annotation and spring common)

2021/02/25 add ActiveMQ test

2022/04/11 add /lock api test, use mysql row lock (select for update)

2022/04/13 add Mysql MVCC test, use RR as default isolation level
2022/04/14 add fix for hql and use pessimistic_write lock

2022/09/14 add test for hibernate session bug
      //hibernate will trigger extra one more update sql in case if original object's fields was modified and you have a native update sql before
      //this is a 'bug' of hibernate session, there are 2 solutions
      //1. dont update any fields of the original object which was fetched from db.
      //2. add session.clear() in userDao.updateUserByFields(u)

2022/09/26  add enum columns test for User.status

2022/10/03  add lazy fetching policy test and toString() should not include the fields need lazy loading

2022/11/3   add transaction and interceptor test, test the order of interceptor
            add 2 solutions for do some logic after transaction was commit
            solution1: set order
            solution2: register TransactionSynchronization
      .