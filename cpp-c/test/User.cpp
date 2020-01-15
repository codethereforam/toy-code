#include<iostream>
using namespace std;

class User {
	public:
		int age;
};

int main() {
	User user;
	user.age = 20;
	cout << user.age << endl;

	User *user1 = new User();
	cout << user1.age << endl;
	delete user1; 
	return 0;
}
