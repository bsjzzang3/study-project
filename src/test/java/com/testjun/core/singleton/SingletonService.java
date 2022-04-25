package com.testjun.core.singleton;

public class SingletonService {

	private static final SingletonService instance = new SingletonService();

	public static SingletonService getInstance() {
		return instance;
	}

	private SingletonService() {
	}

	public void logic() {
		System.out.println("�̱��� ��ü ���� ȣ��");
	}
}
