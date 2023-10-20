# class에서 static

인스턴스 메서드는 인스턴스 변수와 관련된 작업을 하는 메서드이다.   
인스턴스 메서드 내부에서는 인스턴스 변수를 사용한 로직이 사용된다.   

반대로 static 메서드는 인스턴스 변수를 사용할 수 없다. 왜냐하면   
static 메서드는 처음 class가 로드될 때 메모리에 올라가기 때문에    
인스턴스 변수를 사용할 수 없다. 따라서, static 메서드는 인자로 넘어오는 데이터나,    
인스턴스 변수를 사용하지 않을 때 사용하게 된다.   