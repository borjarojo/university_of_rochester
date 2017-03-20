cmake_minimum_required( VERSION 2.8 )
project( csc232 )
set( CMAKE_INSTALL_PREFIX ${CMAKE_BINARY_DIR} CACHE PATH "default install prefix" FORCE )
find_program(GENGETOPT gengetopt)
execute_process( COMMAND ${GENGETOPT} -i ${CMAKE_CURRENT_SOURCE_DIR}/ros_publisher.ggo --file-name ros_publisher_cmdline --output-dir=${CMAKE_CURRENT_BINARY_DIR} --unamed-opts )
execute_process( COMMAND ${GENGETOPT} -i ${CMAKE_CURRENT_SOURCE_DIR}/ros_subscriber.ggo --file-name ros_subscriber_cmdline --output-dir=${CMAKE_CURRENT_BINARY_DIR} --unamed-opts )

set( ROS_PATH_PREFIX /opt/ros/indigo CACHE PATH "default install prefix" FORCE )
find_path(ROS_INCLUDE_DIR NAMES ros/ros.h HINTS CMAKE_INSTALL_PREFIX/include)
include_directories(${ROS_INCLUDE_DIR} ${PROJECT_BINARY_DIR})
file( GLOB ROS_LIBRARIES "${ROS_PATH_PREFIX}/lib/*.so*" )

add_executable( ros_publisher ros_publisher.cpp ${CMAKE_CURRENT_BINARY_DIR}/ros_publisher_cmdline.c )
target_link_libraries( ros_publisher ${ROS_LIBRARIES} )
install( TARGETS ros_publisher DESTINATION bin )

add_executable( ros_subscriber ros_subscriber.cpp ${CMAKE_CURRENT_BINARY_DIR}/ros_subscriber_cmdline.c )
target_link_libraries( ros_subscriber ${ROS_LIBRARIES} )
install( TARGETS ros_subscriber DESTINATION bin )
