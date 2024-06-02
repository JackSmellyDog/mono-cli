package me.shaposhnik.monocli.cli.view;

public interface CommandLineView {
  String toNative();

  String toJson();

  String toTable();
}
